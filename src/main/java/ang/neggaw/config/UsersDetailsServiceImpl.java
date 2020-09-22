package ang.neggaw.config;

import ang.neggaw.dao.UserBankRepository;
import ang.neggaw.entities.UserBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UsersDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserBankRepository userBankRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println("Email: " + email);
        UserBank clientOnline = userBankRepository.findUserBankByUserEmail(email);

        //System.out.println(clientOnline.toString());

        Collection<GrantedAuthority> authorities;

        if (userBankRepository.findUserBankByUserEmail(email) == null)
            throw new RuntimeException("Erreur: utilisateur n'existe pas !!!");
        else {
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(clientOnline.getClient().getNomClient()));
        }

        return new User(clientOnline.getUserEmail(), clientOnline.getPassword(), authorities);
    }
}
