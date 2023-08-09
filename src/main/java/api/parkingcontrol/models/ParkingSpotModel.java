package api.parkingcontrol.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_PARKING_SPOT")
public class ParkingSpotModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    @Column(unique = true, length = 7, nullable = false)
    private String parkingSpotNumber;
    @Column(unique = true, length = 7, nullable = false)
    private String licensePlateCar;
    @Column(unique = false, length = 50, nullable = false)
    private String brandCar;
    @Column(unique = false, length = 50, nullable = false)
    private String modelCar;
    @Column(unique = false, length = 50, nullable = false)
    private String colorCar;
    @Column(unique = false, nullable = false)
    private LocalDateTime registrationDate;
    @Column(unique = false, length = 150, nullable = false)
    private String responsibleName;
    @Column(unique = false, length = 5, nullable = false)
    private String apartment;
    @Column(unique = false, length = 5, nullable = false)
    private String block;


    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getLicensePlateCar() {
        return licensePlateCar;
    }
    public void setLicensePlateCar(String licensePlateCar) {
        this.licensePlateCar = licensePlateCar;
    }
    public String getBrandCar() {
        return brandCar;
    }
    public void setBrandCar(String brandCar) {
        this.brandCar = brandCar;
    }
    public String getModelCar() {
        return modelCar;
    }
    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }
    public String getColorCar() {
        return colorCar;
    }
    public void setColorCar(String colorCar) {
        this.colorCar = colorCar;
    }
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    public String getResponsibleName() {
        return responsibleName;
    }
    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }
    public String getApartment() {
        return apartment;
    }
    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
    public String getBlock() {
        return block;
    }
    public void setBlock(String block) {
        this.block = block;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }
    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }
    
}
