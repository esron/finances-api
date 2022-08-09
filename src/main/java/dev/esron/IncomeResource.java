package dev.esron;

import io.quarkus.hibernate.reactive.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.Path;

@ResourceProperties(path = "incomes")
public interface IncomeResource extends PanacheEntityResource<Income, Long> {
}
