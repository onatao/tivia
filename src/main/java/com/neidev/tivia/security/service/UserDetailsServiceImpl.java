package com.neidev.tivia.security.service;

import com.neidev.tivia.domain.repository.BeneficiarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    private BeneficiarioRepository beneficiarioRepository;

    public UserDetailsServiceImpl(BeneficiarioRepository beneficiarioRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return beneficiarioRepository.findByLogin(username);
    }
}
