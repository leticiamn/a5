package com.example.a5.repositories;

import java.util.List;
import java.util.Optional;

import com.example.a5.entities.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	public void deleteByCpf(String cpfExistente);

	public Optional<Client> findByCpf(String string);

	public void deleteByIncomeGreaterThan(double salarioI);

	public List<Client> findByIncomeGreaterThan(double salarioI);

	public List<Client> findByLowerNameLike(String string);
}
