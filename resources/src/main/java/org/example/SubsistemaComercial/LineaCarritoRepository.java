package org.example.SubsistemaComercial;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaCarritoRepository extends JpaRepository<LineaCarrito, Integer> {
}
