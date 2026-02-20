package lld.lldproblems.ridesharingapp.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class User {
	String userID;
	String name;
	String email;
	String phoneNumber;
}