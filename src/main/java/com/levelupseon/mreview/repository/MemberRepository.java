package com.levelupseon.mreview.repository;

import com.levelupseon.mreview.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
