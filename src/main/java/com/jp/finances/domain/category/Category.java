package com.jp.finances.domain.category;

import com.jp.finances.domain.category.enums.CategoryType;
import com.jp.finances.domain.user.User;
import com.jp.finances.infra.audit.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "finance", name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Category extends Auditable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
