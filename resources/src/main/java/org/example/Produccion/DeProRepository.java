package org.example.Produccion;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeProRepository extends JpaRepository<DesarrolladorProducto, Integer> {
}
