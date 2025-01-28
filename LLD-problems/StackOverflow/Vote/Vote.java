package StackOverflow.Vote;

import StackOverflow.User;

public abstract class Vote {
    private final User user;
    private final VoteType voteType;

    public Vote(User user, VoteType voteType) {
        this.user = user;
        this.voteType = voteType;
    }

    public User getUser() {
        return user;
    }

    public VoteType getVoteType() {
        return voteType;
    }
}
