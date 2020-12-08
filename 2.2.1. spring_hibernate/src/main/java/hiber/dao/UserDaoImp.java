package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> typedQuery = sessionFactory.getCurrentSession().createQuery("from User");
        return typedQuery.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByCar(String model, int series) {
        String hql = "FROM User WHERE car.model = :pModel and car.series = :pSeries";

        TypedQuery<User> typedQuery = sessionFactory.getCurrentSession()
                .createQuery(hql)
                .setParameter("pModel", model)
                .setParameter("pSeries", series);

        return typedQuery.getResultList().get(0);
    }
}
