package com.siemens.library.api.model;

import com.siemens.library.api.entity.ResourceMember;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ResponseToQueryMembers {
    ArrayList<ResourceMember> members;
}
