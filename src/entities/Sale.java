package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Nikola Dragić
 */

@Entity
public class Sale implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int speed;
    private String threshold;
    private int duration;
    private String name;
    private String address;
    
    public int getId() {
        return id;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public String getThreshold() {
        return threshold;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public String getName() {
        return name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ");
        sb.append(String.valueOf(id));
        sb.append(", Speed: ");
        sb.append(String.valueOf(speed));
        sb.append("Mbps, threshold: ");
        sb.append(threshold);
        sb.append(" GB, contract duration: ");
        sb.append(String.valueOf(duration));
        sb.append(" years, name:");
        sb.append(name);
        sb.append(", address:");
        sb.append(address);
        
        return sb.toString();
    }
    
}
