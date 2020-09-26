package ang.neggaw.config;

import ang.neggaw.dao.ClientOnlineRepository;
import ang.neggaw.entities.ClientOnline;
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

    private final ClientOnlineRepository clientOnlineRepository;

    public UsersDetailsServiceImpl(ClientOnlineRepository clientOnlineRepository) { this.clientOnlineRepository = clientOnlineRepository; }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        ClientOnline clientOnline = clientOnlineRepository.findByUsername(email);
        if (clientOnline == null) throw new UsernameNotFoundException("Erreur: utilisateur n'existe pas !!!");
        else {

            Collection<GrantedAuthority> authorities = new ArrayList<>();
            clientOnline.getSituationClientOnlines().forEach(s -> {
                authorities.add(new SimpleGrantedAuthority(s.getNomSituation()));
            });

            return new User(
                    clientOnline.getUsername(),
                    clientOnline.getPassword(),
                    authorities);
            }
        }
}
