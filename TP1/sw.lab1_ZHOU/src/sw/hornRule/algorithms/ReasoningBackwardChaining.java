/**
 * 
 */
package sw.hornRule.algorithms;

import java.util.HashSet;

import sw.hornRule.models.FactBase;
import sw.hornRule.models.Formalism;
import sw.hornRule.models.HornRule;
import sw.hornRule.models.HornRuleBase;
import sw.hornRule.models.Variable;

/**
 * @author  ZHOU Peikun
 *
 */
public class ReasoningBackwardChaining extends AlogrithmChaining {
 

	public boolean entailment(Formalism ruleBase, Formalism factBase, Formalism query) {
		return backwardChaining(ruleBase,factBase,query);
	}

	private boolean backwardChaining(Formalism ruleBase, Formalism factBase,
			Formalism query) {
		// TODO  To complete
		FactBase factBaseSet = new FactBase();
		factBaseSet = (FactBase) factBase;
		HornRuleBase ruleBaseSet = (HornRuleBase) ruleBase;
		if (match(query, factBaseSet.getFact())){
			return true;
		}
		else{
			for (HornRule rule: ruleBaseSet.getRules()){
				if (match(query, ruleBaseSet.getConclusions())){
					boolean bool = true;
					int i = 1;
					while(bool && i <= ruleBaseSet.getRules().size()){
						bool = backwardChaining(rule, factBaseSet, ruleBaseSet);
						i = i + 1;
					}
					if (bool = true){
						return true;
					}
				}
			}
			return false;
		}
	}
	
	private boolean match(Formalism query, HashSet<Variable> hashSet) {
		// TODO Auto-generated method stub
		if (hashSet.contains(query))
			return true;
		else 
			return false;
		}

	@Override
	public int countNbMatches() {
		// TODO To complete
		return 0;
	}

}
