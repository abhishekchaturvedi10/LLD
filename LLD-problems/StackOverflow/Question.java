package StackOverflow;

import StackOverflow.Vote.DownVote;
import StackOverflow.Vote.Upvote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Question {
    private String questionID;
    private String questionTitle;
    private String questionText;
    private String tag;
    private User user;
    private Date creationTime;
    private List<Answer> answers;
    private List<Upvote> upVotes;
    private List<DownVote> downVotes;
    private List<Comment> comments;

    public Question(String questionID, String questionTitle, String questionText, String tag, User user, Date creationTime) {
        this.questionID = questionID;
        this.questionTitle = questionTitle;
        this.questionText = questionText;
        this.tag = tag;
        this.user = user;
        this.creationTime = creationTime;
        this.answers = new ArrayList<>();
        this.upVotes = new ArrayList<>();
        this.downVotes = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public void upVote() {
        this.upVotes.add(new Upvote());
    }
}
