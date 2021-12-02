package bancoDados;

import bancoDados.Gasto;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-12-02T00:38:41", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Cartao.class)
public class Cartao_ { 

    public static volatile SingularAttribute<Cartao, String> idCartao;
    public static volatile SingularAttribute<Cartao, Date> dataValidade;
    public static volatile SingularAttribute<Cartao, String> nome;
    public static volatile SingularAttribute<Cartao, String> bandeira;
    public static volatile CollectionAttribute<Cartao, Gasto> gastoCollection;

}