package StackOverflow;

import java.util.List;

public class StackOverflowService {
    private static StackOverflowService stackOverflowService;
    private List<User> users;
    private List<Question> questions;
    private List<Answer> answers;
    private List<Comment> comments;

    public static StackOverflowService getStackOverflowService() {
        if (stackOverflowService == null) {
            stackOverflowService = new StackOverflowService();
        }
        return stackOverflowService;
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void upVoteQuestion(User user, Question question) {
        question.upVote();
    }


}
