
$(document).ready(function() {
  $(".send_message").click(function(){
    var currentQues = $("#chat-actions-panel").val();
      var requestInProgress = false;
    if(currentQues != "" && !requestInProgress){


        $.ajax({
            url: '/user/chat',
            method: "POST",
            data: JSON.stringify({ message: currentQues, name: "Steve" }),
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
                window.scrollTo(0,document.getElementById('chatArea').scrollHeight);

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
    var recHtml = '',
        tipsHtml = '',
        quesHtml = '',
        promptHtml = '';
    if(typeof(chatResponse.sentiment) !== "undefined" && chatResponse.sentiment != null){
        var mood = chatResponse.sentiment.toLowerCase();
        mood = (mood == 'anxiety') ? 'anxious' : mood;
        recHtml = "<div class=\"tips-div\">It seems you are little bit "+mood+", here are some recommended <a target='_blank' href='suggested-videos/"+mood+"'>videos </a> to make you feel better</div>";
    }
    if(typeof(chatResponse.tips) !== "undefined" && chatResponse.tips != null){
        tipsHtml = '<div class="answer-div">'+chatResponse.tips.replace(/(?:\r\n|\r|\n)/g, '<br>')+'</div>';
    } else if(typeof(chatResponse.prompt) !== "undefined" && chatResponse.prompt != null){
        promptHtml = '<div class="chegg-prompt-div">'+chatResponse.prompt+'</div>';
    }
    if(typeof(chatResponse.questions) !== "undefined" && chatResponse.questions != null){
        quesHtml = '<div class="chegg-ques-div">'+chatResponse.questions+'</div>';
    }
    return '<li class="chegg-reply">'+
            '<div class="chegg-reply-div">'+
                '<img src="img/favicon-s.svg" class="chegg-avatar">'+
                tipsHtml+
                quesHtml+
                recHtml+
                promptHtml+
            '</div>'+
        '</li>';
}
