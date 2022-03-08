package com.nftgram.admin.common.util;

import com.nftgram.admin.admin.common.MemberLoginManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.lang.NonNull;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {

    @Bean
    public AuthorBaseAuditorAwareImpl authorBaseAuditorAware() {
        return new AuthorBaseAuditorAwareImpl();
    }

    private static class AuthorBaseAuditorAwareImpl implements AuditorAware<String> {

        @NonNull
        @Override
        public Optional<String> getCurrentAuditor() {
            String auditorId = null;
            try {
                auditorId = MemberLoginManager.getAuditId();
            } catch (Exception e) {
                return Optional.of("Anonymous");
            }

            return Optional.of(auditorId);
        }
    }
}

