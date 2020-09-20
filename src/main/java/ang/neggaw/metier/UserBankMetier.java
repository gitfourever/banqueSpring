package ang.neggaw.metier;

import ang.neggaw.dao.UserBankRepository;
import ang.neggaw.entities.UserBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserBankMetier implements IUserBankMetier {

    @Autowired
    private UserBankRepository userBankRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String addUserBank(UserBank userBank) {

        if (!userBank.getPassword().equals(userBank.getRepassword()))
            return "Ils existent des mauvaises données";
        else
            userBank.setPassword(passwordEncoder.encode(userBank.getPassword()));
            userBankRepository.save(userBank);

        return "userClient bien créé";
    }

    @Override
    public RuntimeException modifyPasswordUserBank(String email, String oldPass, String newPass, String newRePass) {

        UserBank user = userBankRepository.getOne(email);
        if (!user.getUserEmail().equals(email)) new RuntimeException("erreur user");
        else if (!user.getPassword().equals(oldPass)) return new RuntimeException("Erreur password");
        else if (!newPass.equals(newRePass)) return new RuntimeException("Erreur new passwords");
        else
            user.setPassword(newPass);
            userBankRepository.saveAndFlush(user);
        return null;
    }
}
