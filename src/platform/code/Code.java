package platform.code;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Code implements Comparable<Code> {

    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Type(type = "uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @JsonIgnore
    public static long globalCounter;

    @JsonIgnore
    private long personalCounter;

    private String code;
    private LocalDateTime date = LocalDateTime.now();
    private long views;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long baseTime;
    private long time;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long baseViews;

    public Code(String code, long views, long time) {
        this.code = code;
        this.views = views;
        this.baseTime = time;
        this.time = time;
        this.baseViews = views;
    }

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss")
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public int compareTo(Code code) {
        int dateCmp = code.getDate().compareTo(date);
        if (dateCmp != 0) {
            return dateCmp;
        }
        return Long.compare(code.getPersonalCounter(), personalCounter);
    }
}
