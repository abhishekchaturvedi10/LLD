package StackOverflow;

import StackOverflow.Vote.DownVote;
import StackOverflow.Vote.Upvote;
import StackOverflow.Vote.Vote;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userID;
    private String userName;
    private String password;
    private String email;
    private List<Question> questions;
    private List<Answer> answers;
    private List<Comment> comments;
    private List<Upvote> upVotes;
    private List<DownVote> downVotes;
    private Double reputation;

    public User(String userID, String userName, String password, String email) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.upVotes = new ArrayList<>();
        this.downVotes = new ArrayList<>();
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.reputation = 0.0;
    }
}
