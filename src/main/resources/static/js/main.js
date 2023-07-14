
$(document).ready(function() {
  $(".send_message").click(function(){
    var currentQues = $("#chat-actions-panel").val();
      var requestInProgress = false;
    if(currentQues != "" && !requestInProgress){


        $.ajax({
            url: '/user/chat',
            method: "POST",
            data: JSON.stringify({ message: currentQues, name: "sunil" }),
            dataType: 'json',
            contentType: "application/json",
            beforeSend: function() {
                $("#send-button").html("Sending...")
                $("#send_message").addClass("disable-send");
                requestInProgress =  true;
            },
            success: function(chatResponse,status,jqXHR ){
                console.log(chatResponse);
                $("#chatArea ul").append(getUserQuesArea(currentQues));
                $("#chat-actions-panel").val("");
                $("#send-button").html("Send");
                $("#send_message").removeClass("disable-send");
                requestInProgress =  false;
                $("#chatArea ul").append(getCheggAnswerArea(chatResponse));
            },
            error(jqXHR, textStatus, errorThrown){
                $("#send-button").html("Send");
                $("#send_message").removeClass("disable-send");
                requestInProgress = false
            }
        });

    }

  });
});

function getUserQuesArea(ques){

return '<li class="user-ques">'+
  '<div class="user-ques-div">'+
      '<img class="user-img" src="img/avatar.png">'+
        '<div class="ques-text-div">'+ ques + '</div>'+
    '</div>'+
  '</li>';
}
function getCheggAnswerArea(chatResponse){
    var recHtml,
        tipsHtml,
        quesHtml;
    if(chatResponse.sentiment != null){
        recHtml = "<div class=\"tips-div\"> Here are some recommended videos for you <a target='_blank' href='suggested-videos/"+chatResponse.sentiment.toLowerCase()+"'>videos for you</a></div>";
    }
    if(chatResponse.tips != null){
        tipsHtml = '<div class="answer-div">'+chatResponse.tips.replace(/(?:\r\n|\r|\n)/g, '<br>')+'</div>';
    }

    if(chatResponse.questions != null){
        quesHtml = '<div class="chegg-ques-div">'+chatResponse.questions+'</div>';
    }
    return '<li class="chegg-reply">'+
            '<div class="chegg-reply-div">'+
                '<img src="img/favicon-s.svg" class="chegg-avatar">'+
                tipsHtml+
                quesHtml+
                recHtml+
            '</div>'+
        '</li>';
}
