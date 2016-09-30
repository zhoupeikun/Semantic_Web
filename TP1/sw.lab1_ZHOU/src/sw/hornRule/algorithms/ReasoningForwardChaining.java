/**
 * 
 */
package sw.hornRule.algorithms;

import sw.hornRule.models.FactBase;
import sw.hornRule.models.Formalism;
import sw.hornRule.models.HornRule;
import sw.hornRule.models.HornRuleBase;
import sw.hornRule.models.Variable;

/**
 * @author  ZHOU Peikun
 *
 */
public class ReasoningForwardChaining extends AlogrithmChaining {
 
	/**
	 * @param a knowledge base kb (in a given formalism)
	 * @param facts (in a given formalism)
	 * @return forwardChaining(ruleBase,factBase), also called the saturation of ruleBase w.r.t. factBase, 
	 * mathematically it computes the minimal fix point of KB from facts)
	 */
	//It's your turn to implement the algorithm, including the methods match() and eval()
	public FactBase forwardChaining(Formalism ruleBase, Formalism factBase){
		FactBase factBaseSet = new FactBase();
		factBaseSet = (FactBase) factBase;
		
		HornRuleBase ruleBaseSet = (HornRuleBase) ruleBase;
		
		for(HornRule rule: ruleBaseSet.getRules()){
			if (eval(rule, factBaseSet) == true) {
				factBaseSet.setFact(rule.getConclusions());
			}
		}
		
/*		boolean eval(HornRule rule, FactBase factBase){
			for(Variable condition: rule.getConditions()){
				if (match(condition, factBase) == false) 
						return false;
				else 
					return true;		
				}
			}*/
			
/*		boolean match(Variable condition, FactBase factBase){
			for(Variable fact: factBase.getFact())
				if (condition == fact)
					return true;
				else
					continue;
		}*/
		
		
		return factBaseSet;
	};
	

	private boolean eval(HornRule rule, FactBase factBase) {
		// TODO Auto-generated method stub
		for(Variable condition: rule.getConditions()){
			if (match(condition, factBase) == false) 
					return false;
			else 
				return true;		
			}
		return true;
	}


	private boolean match(Variable condition, FactBase factBase) {
		// TODO Auto-generated method stub
		if(!factBase.getFact().contains(condition))
			return false;
		else
			return true;
	}


	public boolean entailment(Formalism ruleBase, Formalism factBase, Formalism query) {
		FactBase allInferredFacts = forwardChaining(ruleBase, factBase);
		if(allInferredFacts.getFact().contains(query))
			return true;
		else
			return false;
	}

	@Override
	//It's your turn to implement this method
	public int countNbMatches() {
		// TODO Auto-generated method stub
		return 0;
	}

}
