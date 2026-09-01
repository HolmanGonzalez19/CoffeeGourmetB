package com.cgb.coffeegourmetb.specification;

import com.cgb.coffeegourmetb.dto.request.SaleFilterRequest;
import com.cgb.coffeegourmetb.entity.Sale;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public final class SaleSpecification {

    private SaleSpecification() {
    }

    public static Specification<Sale> withFilters(
            SaleFilterRequest filter) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filter == null) {

                return criteriaBuilder.conjunction();

            }

            if (filter.getCajaId() != null) {

                predicates.add(
                        criteriaBuilder.equal(
                                root.get("caja").get("id"),
                                filter.getCajaId()
                        )
                );
            }

            if (filter.getUsuarioId() != null) {

                predicates.add(
                        criteriaBuilder.equal(
                                root.get("usuario").get("id"),
                                filter.getUsuarioId()
                        )
                );
            }

            if (filter.getMetodoPagoId() != null) {

                predicates.add(
                        criteriaBuilder.equal(
                                root.get("metodoPago").get("id"),
                                filter.getMetodoPagoId()
                        )
                );
            }

            if (filter.getEstado() != null) {

                predicates.add(
                        criteriaBuilder.equal(
                                root.get("estado"),
                                filter.getEstado()
                        )
                );
            }

            if (filter.getFechaInicio() != null) {

                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(
                                root.get("fechaHora"),
                                filter.getFechaInicio()
                        )
                );
            }

            if (filter.getFechaFin() != null) {

                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(
                                root.get("fechaHora"),
                                filter.getFechaFin()
                        )
                );
            }

            return criteriaBuilder.and(
                    predicates.toArray(new Predicate[0])
            );
        };
    }
}