package org.example.OperacionesOcultas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraEsclavizadoRepository extends JpaRepository<TrabajadorEsclavizado, Integer> {
}
