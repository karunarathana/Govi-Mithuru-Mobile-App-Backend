package com.govi_mithuro.app.repo;

import com.govi_mithuro.app.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<MessageEntity,Integer> {
}
