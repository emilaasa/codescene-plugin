package org.jenkinsci.plugins.codescene;

import org.jenkinsci.plugins.codescene.Domain.*;

import java.net.URL;
import java.util.List;

public class CodeSceneBuildActionEntry {
    private final String title;
    private final boolean showCommits;
    private final List<Commit> commits;
    private final RiskClassification risk;
    private final List<Warning> warnings;
    private final URL viewUrl;
    private final int riskThreshold;
    private final RiskDescription description;
    private final QualityGates qualityGatesState;
    private final Improvements improvements;
    private final CodeHealthDeltaDescription codeHealthDeltaDescription;
    private final NewFiles newFiles;

    public CodeSceneBuildActionEntry(
            String title,
            boolean showCommits,
            List<Commit> commits,
            RiskClassification risk,
            List<Warning> warnings,
            URL viewUrl,
            int riskThreshold,
            RiskDescription description,
            QualityGates qualityGatesState,
            final Improvements improvements,
            CodeHealthDeltaDescription codeHealthDeltaDescription,
            NewFiles newFiles) {
        this.title = title;
        this.showCommits = showCommits;
        this.commits = commits;
        this.risk = risk;
        this.improvements = improvements;
        this.warnings = warnings;
        this.viewUrl = viewUrl;
        this.riskThreshold = riskThreshold;
        this.description = description;
        this.qualityGatesState = qualityGatesState;
        this.codeHealthDeltaDescription = codeHealthDeltaDescription;
        this.newFiles = newFiles;
    }

    public String getTitle() {
        return title;
    }

    public boolean getShowCommits() {
        return showCommits;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public RiskClassification getRisk() {
        return risk;
    }

    public RiskDescription getDescription() { return description; }

    public List<Warning> getWarnings() {
        return warnings;
    }

    public boolean getHasWarnings() {
        return warnings != null && !warnings.isEmpty();
    }

    public URL getViewUrl() {
        return viewUrl;
    }

    public int getRiskThreshold() {
        return riskThreshold;
    }

    public boolean getHitsRiskThreshold() {
        return risk != null && risk.getValue() >= riskThreshold;
    }

    public QualityGates gates() { return this.qualityGatesState; }

    public boolean getGoalHasFailed() {
        return qualityGatesState != null && qualityGatesState.goalHasFailed();
    }

    public boolean getHasImprovements() {
        return improvements != null && !improvements.value().isEmpty();
    }

    public List<String> getImprovements() { return improvements.value(); }

    public boolean getCodeHealthDeclined() {
        return qualityGatesState != null && qualityGatesState.codeHealthDeclined();
    }

    public boolean getQualityGatesEnabled() {
        return qualityGatesState != null && qualityGatesState.enabled();
    }

    public String getCodeOwners() {
        if (qualityGatesState != null) {
            return qualityGatesState.codeOwners();
        }

        return "-";
    }

    public boolean getHasCodeOwners() { return qualityGatesState != null && qualityGatesState.hasCodeOwners();}

    public boolean getHasCodeHealthDeltaDescriptions() { return !codeHealthDeltaDescription.deltaDescriptions().isEmpty(); }
    public List<CodeHealthDelta> getCodeHealthDeltaDescriptions() { return codeHealthDeltaDescription.deltaDescriptions(); }

    public boolean getHasNewFilesInfo() { return newFiles.hasNewFileInfo(); }
    public String getNewFilesSummary() { return newFiles.getSummary(); }
    public List<ReviewOfNewFile> getReviewOfNewFiles() { return newFiles.getReviews(); }
}
