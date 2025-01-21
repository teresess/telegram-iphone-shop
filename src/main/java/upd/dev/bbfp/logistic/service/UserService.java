package upd.dev.bbfp.logistic.service;

import org.springframework.stereotype.Service;
import upd.dev.bbfp.logistic.model.User;
import upd.dev.bbfp.logistic.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    public UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElse(null);
    }
    public boolean isAdmin(Long userId) {
        return userRepository.findById(userId).get().getRole().equals("admin");
    }
    public void regUser(User user) {
        userRepository.save(user);
    }
    public void setAdmin(Long userId) {
        User user = getUser(userId);
        user.setRole("admin");
        userRepository.save(user);
    }
}
