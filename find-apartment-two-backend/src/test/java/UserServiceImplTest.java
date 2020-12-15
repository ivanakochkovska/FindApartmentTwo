import com.example.findapartmentbackend.Exceptions.FindApartmentBusinessException;
import com.example.findapartmentbackend.model.Ad;
import com.example.findapartmentbackend.model.User;
import com.example.findapartmentbackend.model.UserAd;
import com.example.findapartmentbackend.repository.AdRepository;
import com.example.findapartmentbackend.repository.UserAdRepository;
import com.example.findapartmentbackend.repository.UserRepository;
import com.example.findapartmentbackend.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AdRepository adRepository;
    @Mock
    private UserAdRepository userAdRepository;

    @Test
    public void findUserByUsernameTest() {
        //Given
        User user = new User();
        user.setUsername("ivanak");
        user.setPassword("ivana");
        user.setFirstName("ivana");
        user.setSurname("ivana");
        userRepository.save(user);
        userRepository.flush();
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);
        String username = "ivanak";
        //when
        User user1 = userService.findUserByUsername(username).get();
        //then
        assertEquals("ivanak",user1.getUsername());
    }

    @Test
    public void saveBookmarkTest() {
        Ad ad = new Ad();
        List<User> users = new ArrayList<>();
        ad.setPhoneNumber("000");
        ad.setId(1L);
        adRepository.save(ad);
        adRepository.flush();
        Long id = 1L;
        User user = new User();
        user.setUsername("ivanak");
        user.setPassword("ivana");
        user.setFirstName("ivana");
        user.setSurname("ivana");
        userRepository.save(user);
        userRepository.flush();
        users.add(user);
        Optional<Ad> ad1;
        when((adRepository.findById(1L))).thenReturn(Optional.of(ad));
       ad1 = (adRepository.findById(id));
        System.out.println(ad1);
        assertEquals(ad1.get(),ad);
        Optional<User> user1;
        when(userRepository.findAll()).thenReturn(users);
        user1 = userService.findUserByUsername("ivanak");
        assertEquals(user1.get(),user);
        //then
        UserAd userAd = new UserAd();
        userAd.setUser(user);
        userAd.setAd(ad);
        userAdRepository.save(userAd);
        userService.saveBookmark(id,user.getUsername());
    }

    @Test
    public void loginUserTest() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setUsername("ivanak");
        user.setPassword("ivana");
        user.setFirstName("ivana");
        user.setSurname("ivana");
        User user1 = new User();
        user1.setUsername("ivana");
        user1.setPassword("ivana");
        user1.setFirstName("ivana");
        user1.setSurname("ivana");
        users.add(user);
        users.add(user1);
        when(userRepository.findAll()).thenReturn(users);
        String username = "ivanak";
        String password = "ivana";
        User userFound = userService.loginUser(username,password).get();
        assertEquals(user,userFound);
    }

    @Test
    public void saveUserWithException() {

        User user = new User();
        user.setUsername("ivanak");
        user.setPassword("ivana");
        user.setFirstName("ivana");
        user.setSurname("ivana");
        List<User> users = new ArrayList<>();
        users.add(user);
        userRepository.save(user);
        when(userRepository.findAll()).thenReturn(users);
        //userService.saveUser("ivana","ivana","ivana","ivanak");
        assertThatThrownBy(()->userService.saveUser
                ("ivana","ivana","ivana","ivanak")).isInstanceOf(FindApartmentBusinessException.class);
    }

    @Test
    public void saveUserWithoutException() {
        User user = new User();
        user.setUsername("ivanak");
        user.setPassword("ivana");
        user.setFirstName("ivana");
        user.setSurname("ivana");
        List<User> users = new ArrayList<>();
        users.add(user);
        userRepository.save(user);
        userService.saveUser("ivana","ivana","ivana","ivana");
        verify(userRepository,times(1)).save(user);
    }

}
