package com.siemens.library.api.service.impl;

import com.siemens.library.api.entity.ResourceMember;
import com.siemens.library.api.mapper.ServiceMapper;
import com.siemens.library.api.model.Error;
import com.siemens.library.api.model.LibraryResponse;
import com.siemens.library.api.model.RequestBodyToCreateMember;
import com.siemens.library.api.repository.MembershipRepository;
import com.siemens.library.api.service.MembershipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.siemens.library.api.util.LibraryUtil.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {
    private final MembershipRepository repository;
    private final ServiceMapper serviceMapper;
    
    @Override
    public LibraryResponse createNewMember(RequestBodyToCreateMember memberRequest) {
        StringBuilder em = new StringBuilder();
        StringBuilder ec = new StringBuilder();
        boolean hasError = checkStructureError(memberRequest, em,ec);
        if (hasError) {
            return new LibraryResponse(HttpStatus.BAD_REQUEST, FAILED, "", new Error(em.toString(), ec.toString()));
        }
        ResourceMember member = ResourceMember.builder().
                name(memberRequest.getName()).
                surname(memberRequest.getSurname()).
                email(memberRequest.getEmail()).
                phoneNumber(memberRequest.getPhoneNumber()).
                joinDate(memberRequest.getJoinDate()).
                build();
        return new LibraryResponse(HttpStatus.OK, SUCCEED, serviceMapper.memberToDto(repository.save(member)), null);
    }

    private boolean checkStructureError(RequestBodyToCreateMember member, StringBuilder em, StringBuilder ec) {
        boolean hasError = true;
        SimpleDateFormat sdf = new SimpleDateFormat(publishDateFormat);

        if (member == null || member.getName() == null || member.getSurname() == null || member.getEmail() == null) {
            em.append(REQUIRED_OBJ_MISSING_MEMBER_ERROR);
            ec.append(REQUIRED_OBJ_MISSING_MEMBER_ERROR_CODE);
        }
        //check name
        else if (member.getName().length() > nameLength) {
            em.append(NAME_LENGTH_ERROR);
            ec.append(NAME_LENGTH_ERROR_CODE);
        }
        //check surname
        else if (member.getSurname().length() > surnameLength) {
            em.append(SURNAME_LENGTH_ERROR);
            ec.append(SURNAME_LENGTH_ERROR_CODE);
        }
        //check email
        else if (member.getEmail().length() > emailLength) {
            em.append(EMAIL_LENGTH_ERROR);
            ec.append(EMAIL_LENGTH_ERROR_CODE);
        }
        //check phone
        else if (member.getPhoneNumber() != null && member.getPhoneNumber().length() > phoneNumberLength) {
            em.append(PHONE_NUMBER_LENGTH_ERROR);
            ec.append(PHONE_NUMBER_LENGTH_ERROR_CODE);
        } else {
            // no errror found
            hasError = false;
            //check publishing Date we cannot put inside if else block in
            // order to reduce redundancy of if else block code because of exception thrown on wrong parsing
            try {
                sdf.parse(member.getJoinDate());
            } catch (ParseException e) {
                log.error("ParseException", e);
                em.append(PUBLISH_DATE_PARSE_ERROR);
                ec.append(PUBLISH_DATE_PARSE_ERROR_CODE);
                hasError = true;
            }
        }
        return hasError;
    }
}
