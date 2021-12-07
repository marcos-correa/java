package bancoDados;

import bancoDados.Cartao;
import bancoDados.TipoGasto;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2021-12-07T03:03:27", comments="EclipseLink-2.7.7.v20200504-rNA")
@StaticMetamodel(Gasto.class)
public class Gasto_ { 

    public static volatile SingularAttribute<Gasto, Integer> idGasto;
    public static volatile SingularAttribute<Gasto, Cartao> idCartao;
    public static volatile SingularAttribute<Gasto, TipoGasto> idTipoGasto;
    public static volatile SingularAttribute<Gasto, Date> data;
    public static volatile SingularAttribute<Gasto, Float> valor;
    public static volatile SingularAttribute<Gasto, String> idFormaPagamento;

}