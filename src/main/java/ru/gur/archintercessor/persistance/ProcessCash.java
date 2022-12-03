package ru.gur.archintercessor.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("processCash")
public class ProcessCash implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String processId;

    private UUID profileId;

    @TimeToLive
    private Long timeout;
}
