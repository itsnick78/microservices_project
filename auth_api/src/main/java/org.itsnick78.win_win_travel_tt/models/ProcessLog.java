package org.itsnick78.win_win_travel_tt.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "process_log")
public class ProcessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "input_text")
    private String inputText;
    @Column(name = "output_text")
    private String outputText;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public ProcessLog(User user, String inputText, String outputText) {
        this.user = user;
        this.inputText = inputText;
        this.outputText = outputText;
    }

    public ProcessLog() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getOutputText() {
        return outputText;
    }

    public void setOutputText(String outputText) {
        this.outputText = outputText;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
