/**
 * 
 */
package sw.hornRule.algorithms;

import java.util.ArrayList;
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
public class ReasoningForwardChainingOptimised extends AlogrithmChaining {
	
	/**
	 * @param a knowledge base ruleBase (in a given formalism)
	 * @param a base of facts : factBase (in a given formalism)
	 * @return the saturation of KB w.r.t. facts (the minimal fix point of KB from facts)
	 */
	public FactBase forwardChainingOptimise(Formalism ruleBase, Formalism factBase){
		//It's your turn to implement the algorithm
		FactBase factBaseSet = new FactBase();
		factBaseSet = (FactBase) factBase;
		
		HornRuleBase ruleBaseSet = (HornRuleBase) ruleBase;
		
		for (Variable fact: factBaseSet.getFact()){
			factBaseSet.setFact(Propagate(fact, ruleBase).getFact());
		}
			
		return factBaseSet;
	};
 
	private FactBase Propagate(Variable fact, Formalism ruleBase) {
		// TODO Auto-generated method stub
		FactBase factSet;
		HornRuleBase ruleBaseSet = (HornRuleBase) ruleBase;
		if(ruleBaseSet.getConditions().contains(fact)){
		for (HornRule rule: ruleBaseSet.getRules()){
			HashSet<Variable> conditions = rule.getConditions()
			for (Variable atomCondition: conditions){
				if (match(atomCondition, rule.getConditions())){
					conditions.remove(atomCondition);
				}
			}
			if (conditions.isEmpty()){
				ruleBaseSet.remove(rule);
				factSet.setFact(rule.getConclusions());
			}
		}
		FactBase factBaseSet = factSet;
		for(Variable factatom: factSet.getFact()){
			factBaseSet.setFact(Propagate(fact, ruleBase));
		}
		return factBaseSet;
	}
	}



	private boolean match(Variable condition, FactBase factBase) {
		// TODO Auto-generated method stub
		if(!factBase.getFact().contains(condition))
			return false;
		else
			return true;
	}



	public boolean entailment(Formalism ruleBase, Formalism factBase, Formalism query) {
		FactBase allInferredFacts = forwardChainingOptimise(ruleBase, factBase);
		if(allInferredFacts.getFact().contains(query))
			return true;
		else
			return false;
	}

	@Override
	public int countNbMatches() {
		// TODO Auto-generated method stub
		return 0;
	}

}
