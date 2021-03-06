package com.petstore.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pet_animal")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    private String breed;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PetTypes types;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PetSex  sex;

    private Integer age;

    @ManyToOne()
    @JoinColumn(name = "store_pets")
    private Store petStore;

    private Date birthDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetTypes getTypes() {
        return types;
    }

    public void setTypes(PetTypes type) {
        this.types = type;
    }

    public PetSex getSex() {
        return sex;
    }

    public void setSex(PetSex sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Store getPetStore() {
        return petStore;
    }

    public void setPetStore(Store petStore) {
        this.petStore = petStore;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pet{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", breed='").append(breed).append('\'');
        sb.append(", types=").append(types);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", birthDate=").append(birthDate);
        sb.append('}');
        return sb.toString();
    }
}
