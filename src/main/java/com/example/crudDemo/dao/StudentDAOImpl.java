package com.example.crudDemo.dao;

import com.example.crudDemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    //define fields for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
     @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    //implements the save method
    @Override
    @Transactional  //as we are performing an update
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {
         //create query
       // TypedQuery<Student>  theQuery=entityManager.createQuery("FROM Student order by firstName", Student.class);
        TypedQuery<Student>  theQuery=entityManager.createQuery("FROM Student", Student.class);


        //return quesry result

        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByFirstName(String theFirstName) {
         //create query
        TypedQuery<Student> theQuery=entityManager.createQuery("FROM Student where firstName=:theData",Student.class);
        //:theData is a placeholder that is filled in letter , JPQL Named parameters are prefixed with a colon:

        theQuery.setParameter("theData",theFirstName);
        // return query results


        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
         //Retrieve student
        Student student=entityManager.find(Student.class,id);
    //delete the stduent
        entityManager.remove(student);
     }

    @Override
    @Transactional
    public int deleteAll() {
         int numRowsDeleted=entityManager.createQuery("DELETE FROM Student").executeUpdate();

        return numRowsDeleted;
    }

}
