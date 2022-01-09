package pl.pabjan.schoolmanagementsystem.model;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * abstract class which configures how model is created
 * it doesn't create table in database
 */
@MappedSuperclass
@Data
public abstract class AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public int hashCode() {
        if(getId()!=null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) {
            return true;
        }
        if(obj==null) {
            return false;
        }
        if(getClass()!=obj.getClass()) {
            return false;
        }
        AbstractModel other = (AbstractModel) obj;
        if(getId()==null || other.getId() == null) {
            return false;
        }
        return getId().equals(other.getId());
    }
}
