package com.gmail.merikbest2015.mapper;

import com.gmail.merikbest2015.UserServiceTestHelper;
import com.gmail.merikbest2015.constants.UserSuccessMessage;
import com.gmail.merikbest2015.dto.request.EndRegistrationRequest;
import com.gmail.merikbest2015.dto.request.RegistrationRequest;
import com.gmail.merikbest2015.dto.response.AuthenticationResponse;
import com.gmail.merikbest2015.repository.projection.AuthUserProjection;
import com.gmail.merikbest2015.service.RegistrationService;
import com.gmail.merikbest2015.commons.util.TestConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RegistrationMapperTest {

    @InjectMocks
    private RegistrationMapper registrationMapper;

    @Mock
    private RegistrationService registrationService;

    @Mock
    private AuthenticationMapper authenticationMapper;

    private final BindingResult bindingResult = mock(BindingResult.class);

    @Test
    public void registration() {
        RegistrationRequest request = new RegistrationRequest();
        request.setBirthday(TestConstants.BIRTHDAY);
        request.setEmail(TestConstants.USER_EMAIL);
        request.setUsername(TestConstants.USERNAME);
        when(registrationService.registration(request, bindingResult)).thenReturn(UserSuccessMessage.USER_DATA_CHECKED);
        assertEquals(UserSuccessMessage.USER_DATA_CHECKED, registrationMapper.registration(request, bindingResult));
        verify(registrationService, times(1)).registration(request, bindingResult);
    }

    @Test
    public void sendRegistrationCode() {
        when(registrationService.sendRegistrationCode(TestConstants.USER_EMAIL, bindingResult)).thenReturn(UserSuccessMessage.REGISTRATION_CODE_SENT);
        assertEquals(UserSuccessMessage.REGISTRATION_CODE_SENT, registrationMapper.sendRegistrationCode(TestConstants.USER_EMAIL, bindingResult));
        verify(registrationService, times(1)).sendRegistrationCode(TestConstants.USER_EMAIL, bindingResult);
    }

    @Test
    public void checkRegistrationCode() {
        when(registrationService.checkRegistrationCode(TestConstants.ACTIVATION_CODE)).thenReturn(UserSuccessMessage.USER_SUCCESSFULLY_ACTIVATED);
        assertEquals(UserSuccessMessage.USER_SUCCESSFULLY_ACTIVATED, registrationMapper.checkRegistrationCode(TestConstants.ACTIVATION_CODE));
        verify(registrationService, times(1)).checkRegistrationCode(TestConstants.ACTIVATION_CODE);
    }

    @Test
    public void endRegistration() {
        EndRegistrationRequest request = new EndRegistrationRequest();
        request.setEmail(TestConstants.USER_EMAIL);
        request.setPassword(TestConstants.PASSWORD);
        AuthUserProjection authUserProjection = UserServiceTestHelper.createAuthUserProjection();
        Map<String, Object> user = Map.of("user", authUserProjection, "token", TestConstants.AUTH_TOKEN);
        AuthenticationResponse response = new AuthenticationResponse();
        when(registrationService.endRegistration(TestConstants.USER_EMAIL, TestConstants.PASSWORD, bindingResult)).thenReturn(user);
        when(authenticationMapper.getAuthenticationResponse(user)).thenReturn(response);
        assertEquals(response, registrationMapper.endRegistration(request, bindingResult));
        verify(registrationService, times(1)).endRegistration(TestConstants.USER_EMAIL, TestConstants.PASSWORD, bindingResult);
        verify(authenticationMapper, times(1)).getAuthenticationResponse(user);
    }
}
