package com.codingninjas.EVotingSystem.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codingninjas.EVotingSystem.entities.Election;
import com.codingninjas.EVotingSystem.entities.ElectionChoice;
import com.codingninjas.EVotingSystem.entities.User;
import com.codingninjas.EVotingSystem.entities.Vote;
import com.codingninjas.EVotingSystem.repositories.ElectionChoiceRepository;
import com.codingninjas.EVotingSystem.repositories.ElectionRepository;
import com.codingninjas.EVotingSystem.repositories.UserRepository;
import com.codingninjas.EVotingSystem.repositories.VoteRepository;

@Service
public class EVotingService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ElectionRepository electionRepository;

    @Autowired
    ElectionChoiceRepository electionChoiceRepository;

    public List<Vote> getAllVotes() {
      return  voteRepository.findAll();
    }

    public void addUser(User user) {
//        User users = new User();
//        users.setId(user.getId());
//        users.setName(user.getName());
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

    public void addVote(Long userId, Long electionId, Long electionChoiceId) {
        if (AlreadyGivenVote(userId,electionId)){
            return;
        }
       User user = userRepository.findById(userId).get();
        ElectionChoice electionChoice = electionChoiceRepository.findById(electionChoiceId).get();
        Election election = electionRepository.findById(electionId).get();
        Vote vote = new Vote();
        vote.setUser(user);
        vote.setElectionChoice(electionChoice);
        vote.setElection(election);
        voteRepository.save(vote);
    }

    public void addElection(Election election) {
//        Election ele = new Election();
//        ele.setId(election.getId());
//        ele.setName(election.getName());
        electionRepository.save(election);
    }

    public boolean AlreadyGivenVote(Long userId, Long electionId) {
//        User user = userRepository.findById(userId).get();
//        Election election = electionRepository.findById(electionId).get();
        Vote vote = voteRepository.findByUserIdAndElectionId(userId,electionId);
//        List<Vote> allVote = election.getVotes();
//        for (Vote vote : allVote){
//            if (vote.getUser() == user) return true;
//        }
        if(vote == null){
            return false;
        }
        return true;
    }

    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }

    public void addElectionChoice(ElectionChoice electionChoice) {
//        ElectionChoice electionChoice1 = new ElectionChoice();
//        electionChoice1.setElection(electionChoice.getElection());
//        electionChoice1.setId(electionChoice.getId());
//        electionChoice1.setName(electionChoice.getName());
       Election election = electionRepository.findByName(electionChoice.getElection().getName());
       electionChoice.setElection(election);
        electionChoiceRepository.save(electionChoice);
    }

    public List<ElectionChoice> getAllElectionChoices() {
        return electionChoiceRepository.findAll();
    }

//    public Election findElectionByName(String electionName) {
//        return electionRepository.findByName(electionName);
//    }

    public long countTotalVotes() {
       List<Vote> allVotes = voteRepository.findAll();
       return allVotes.size();
    }

    public long countVotesByElectionName(String electionName) {
//        List<Vote> allVote = voteRepository.findAll();
//        return allVote
//                .stream()
//                .filter(v -> v.getElection().getName().equals(electionName))
//                .count();
        return voteRepository.countVotesByElectionName(electionName);
    }

    public long choicesByElection(Long electionId) {
            return electionChoiceRepository.choicesByElection(electionId);

    }

	public ElectionChoice findElectionWinner(String electionName) {
        Election election = electionRepository.findByName(electionName);
        long electionId = election.getId();
            return electionChoiceRepository.findElectionChoiceWithMaxVotes(electionId);
	}
}
