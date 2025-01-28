package StackOverflow;

import StackOverflow.Vote.DownVote;
import StackOverflow.Vote.Upvote;
import StackOverflow.Vote.Vote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Answer {
    private String answerID;
    private String answerText;
    private Question question;
    private User user;
    private Date creationTime;
    private List<Upvote> upVotes;
    private List<DownVote> downVotes;
    private List<Comment> comments;
    private boolean isAccepted;

    public Answer(String answerID, String answerText, Question question, User user, Date creationTime) {
        this.answerID = answerID;
        this.answerText = answerText;
        this.question = question;
        this.user = user;
        this.creationTime = creationTime;
        this.upVotes = new ArrayList<>();
        this.downVotes = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.isAccepted = false;
    }
}
