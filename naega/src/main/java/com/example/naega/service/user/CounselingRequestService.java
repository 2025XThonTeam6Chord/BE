package com.example.naega.service.user;

import com.example.naega.dto.dashboard.CounselingRequestListRes;
import com.example.naega.repository.user.CounselingRequestRepository;
import com.example.naega.repository.user.CounselingUserProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CounselingRequestService {
    private final CounselingRequestRepository counselingRequestRepository;

    public CounselingRequestListRes getDashboardCounselingReserveList() {
        List<CounselingUserProjection> projections = counselingRequestRepository.findCounselingRequestListWithUserDetailsAndReportScore();

        List<CounselingRequestListRes.CounselingUser> counselingUsers = projections.stream()
                .map(projection -> new CounselingRequestListRes.CounselingUser(
                        projection.getName(),
                        String.valueOf(projection.getStudentNumber()),
                        projection.getStudentUniv(),
                        projection.getStudentMajor()
                ))
                .collect(Collectors.toList());

        return new CounselingRequestListRes(counselingUsers);
    }
}
