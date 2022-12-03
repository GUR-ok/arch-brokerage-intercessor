package ru.gur.archintercessor.persistance;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(prefix = "app", name = "redis.enabled")
public interface RedisRepository extends CrudRepository<ProcessCash, String> {
}
