package prometheus.backend.webflux.esp32.espinformationprocessorapi.EspInformation.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("position")
public class Position {
    @Id
    private Long id;
    private String name;
    private Integer current;
    private Integer to_position;
    private String ip_address;
    private String port;
    private Integer mode;
    private Integer motor_id;
}
