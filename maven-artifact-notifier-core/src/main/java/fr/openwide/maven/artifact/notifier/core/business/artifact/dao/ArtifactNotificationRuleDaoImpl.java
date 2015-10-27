package fr.openwide.maven.artifact.notifier.core.business.artifact.dao;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQuery;

import fr.openwide.core.jpa.business.generic.dao.GenericEntityDaoImpl;
import fr.openwide.maven.artifact.notifier.core.business.artifact.model.ArtifactNotificationRule;
import fr.openwide.maven.artifact.notifier.core.business.artifact.model.FollowedArtifact;
import fr.openwide.maven.artifact.notifier.core.business.artifact.model.QArtifactNotificationRule;

@Repository("artifactNotificationRuleDao")
public class ArtifactNotificationRuleDaoImpl extends GenericEntityDaoImpl<Long, ArtifactNotificationRule> implements IArtifactNotificationRuleDao {
	
	private static final QArtifactNotificationRule qArtifactNotificationRule = QArtifactNotificationRule.artifactNotificationRule;
	
	@Override
	public ArtifactNotificationRule getByFollowedArtifactAndRegex(FollowedArtifact followedArtifact, String regex) {
		JPAQuery<ArtifactNotificationRule> query = new JPAQuery<>(getEntityManager());
		
		query
			.select(qArtifactNotificationRule)
			.from(qArtifactNotificationRule)
			.where(qArtifactNotificationRule.followedArtifact.eq(followedArtifact))
			.where(qArtifactNotificationRule.regex.eq(regex));
		
		return query.fetchOne();
	}
}
