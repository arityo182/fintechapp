package com.fintechapp.auth_users.services;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.fintechapp.auth_users.dto.UpdatePasswordRequest;
import com.fintechapp.auth_users.dto.UserDTO;
import com.fintechapp.auth_users.entity.User;
import com.fintechapp.res.Response;

public interface UserService {

    User getCurrentLoggedInUser();

    Response<UserDTO> getMyProfile();

    Response<Page<UserDTO>> getAllUsers(int page, int size);

    Response<?> updatePassword(UpdatePasswordRequest updatePasswordRequest);

    Response<?> uploadProfilePicture(MultipartFile file);

    Response<?> uploadProfilePictureTo53(MultipartFile file);
}
