import java.awt.List;

public class Demo{
	public void selectMeal(String criteria){

    List<AbstractMeal> l = new List<AbstractMeal>;
    for (AbstractMeal m : ClientConsole.re1.getMeal_list()){
        switch(criteria){
        case "AsItIs":
            if (m.asItIsCount >= m.ModifiedCount && m.asItIsCount >=m.specialOfferCount){
                l.add(m);
            }
            break;
        case "AsModified":
            if (m.modifiedCount >= m.asItIsCount && m.modifiedCount >=m.specialOfferCount){
                l.add(m);
            }
        case "SpecialOffer":
            if (m.specialOfferCount >= m.asItIsCount && m.specialOfferCount >= m.modifiedCount){
                l.add(m);
            }
        }
    }
}
