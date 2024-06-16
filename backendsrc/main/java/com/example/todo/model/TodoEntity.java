package com.example.todo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Todo")

public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동으로 ID 생성
    private String id;
    private String userId;
    private String title;
    private boolean done;
}
