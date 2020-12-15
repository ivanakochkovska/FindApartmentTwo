import com.example.findapartmentbackend.model.UserAd;
import com.example.findapartmentbackend.repository.UserAdRepository;
import com.example.findapartmentbackend.service.UserAdServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserAdServiceImplTest {
    @InjectMocks
    UserAdServiceImpl userAdService;
    @Mock
    UserAdRepository userAdRepository;

    @Test
    public void deleteAd() {
        Long id = 1L;
        UserAd userAd = new UserAd();
        userAd.setId(id);
        when(userAdRepository.findById(id)).thenReturn(Optional.of(userAd));
        userAdService.deleteAd("1");
    }

}
