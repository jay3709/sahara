package com.chegg.sahara.configuration;

public class Constants {

    // roles
    public final static String userRole = "user";
    public final static String systemRole = "system";
    public final static String assistantRole = "assistant";


    // prefixes
    public final static String context = "The user is a student seeking help.";
    public final static String usersFeedbackOnConversation = "If user's message is like a feedback, provide proper response";
    public final static String keyRoleOfAssistant = "As a therapist,";
    public final static String taskOfAssistant = " Put all response in a json object with Json Key and Json Value.";
    public final static String outOfContextMessage = "If user's message is out of context then reply with Json Key: prompt, Json Value: I can not help with this right now, if there is anything through which I can make you feel better, please continue.";
    public final static String tipsMessage = "Json Key: tips, Json Value: Address user with text Dear <user's name> <newline> and provide tips for help.";
    public final static String sentimentMessage = "Json Key: sentiment, Json Value: Sentiment analysis so far in one word among ";
    public final static String questionsMessage = "Json Key: questions, Json Value: Ask for relevant questions.";
    public final static String exampleMessageForContext = "Example of response when context of user's message is in scope: \n" +
            "{" +
            "\"tips\":\"Tell a trusted adult about the bullying incidents. They can provide support and help in " +
            "addressing the situation.\",\n" +
            "\"sentiment\":\"sad\",\n" +
            "\"questions\":\"Can you provide more details about the bullying incidents? Who is involved and what kind" +
            " of bullying are you experiencing?\"\n" +
            "}.";
    public final static String exampleMessageForOutOfContext = "Example of response when context of user's message is not in scope: \n" +
            "{\"prompt\": \"I can not help with this right now}.";

}
