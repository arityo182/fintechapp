package com.fintechapp.auth_users.services;

import com.fintechapp.auth_users.dto.LoginRequest;
import com.fintechapp.auth_users.dto.LoginResponse;
import com.fintechapp.auth_users.dto.RegistrationRequest;
import com.fintechapp.auth_users.dto.ResetPasswordRequest;
import com.fintechapp.res.Response;

public interface AuthService {

    Response<String> register(RegistrationRequest request);

    Response<LoginResponse> login(LoginRequest loginrequest);

    Response<?> forgetPassword(String email);

    Response<?> updatePasswordViaResetCode(ResetPasswordRequest resetPasswordRequest);
}
