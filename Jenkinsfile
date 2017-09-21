node("master"){
    def svnRevision = 0;
	stage("start"){ 
	    def mvnHome = "${env.M2_HOME}";
		env.PATH = "${env.PATH}:${mvnHome}/bin"
		sh 'date' 
	}
	
	stage("CheckOut"){ 
		def sinfo = checkout([$class: 'SubversionSCM', additionalCredentials: [], excludedCommitMessages: '', excludedRegions: '', excludedRevprop: '', excludedUsers: '', filterChangelog: false, ignoreDirPropChanges: false, includedRegions: '', locations: [[credentialsId: '258', depthOption: 'infinity', ignoreExternalsOption: true, local: '.', remote: 'http://192.168.1.200:8055/svn/SQES_DEMO/trunk/ersp']], workspaceUpdater: [$class: 'UpdateUpdater']])
		svnRevision = sinfo["SVN_REVISION"];
		env.SVN_REVISION = svnRevision;
	}
	
	
	stage ("clean"){
		sh 'mvn clean'
	}

	stage("package"){ 
	    try{
		    sh 'mvn package -Dmaven.test.skip=true' 
	    }catch(err){
	        //error 'CompileFailException'
	        currentBuild.result = 'FAILURE'
	    }
	}

	stage("QA:FindBugs"){ 
	    if ('FAILURE'.equals(currentBuild.result)){
		    print 'Compile Error! Skip Code Check! - FindBugs'
		}else{
	        sh 'mvn findbugs:findbugs'
	    }
	}

	stage("QA:PMD"){
	    if ('FAILURE'.equals(currentBuild.result)){
		    print 'Compile Error! Skip Code Check! - PMD'
		}else{
		    sh 'mvn pmd:pmd'
		}
	}
	
	stage ("QA:CPD"){ 
		if ('FAILURE'.equals(currentBuild.result)){
		    print 'Compile Error! Skip Code Check! - CPD'
		}else{
		    sh 'mvn pmd:cpd'
		}
	}

	stage ("QA:Checkstyle"){
	    if ('FAILURE'.equals(currentBuild.result)){
		    print 'Compile Error! Skip Code Check! - Checkstyle'
		}else{
		    sh 'mvn checkstyle:checkstyle'
		}
	}

	stage ("QA:Result Collecting"){ 
		//junit '**/target/surefire-reports/**.xml'
		if ('FAILURE'.equals(currentBuild.result)){
		    print 'Compile Error! Skip Code Check Result Collect'
		}else{
		    findbugs canComputeNew: false, defaultEncoding: 'UTF-8', excludePattern: '', failedTotalAll: '20', failedTotalHigh: '15', failedTotalLow: '5', failedTotalNormal: '10', healthy: '', includePattern: '', pattern: 'target/report/qa/findbugs/findbugsXml.xml', unHealthy: '', unstableTotalAll: '4', unstableTotalHigh: '3', unstableTotalLow: '1', unstableTotalNormal: '2'
            pmd canComputeNew: false, defaultEncoding: 'UTF-8', healthy: '', pattern: 'target/report/qa/pmd/pmd.xml', unHealthy: ''
            dry canComputeNew: false, defaultEncoding: 'UTF-8', failedTotalAll: '30', failedTotalHigh: '20', failedTotalLow: '5', failedTotalNormal: '10', healthy: '', pattern: 'target/report/qa/pmd/cpd.xml', unHealthy: ''
		    checkstyle canComputeNew: false, defaultEncoding: 'UTF-8', healthy: '', pattern: 'target/report/qa/checkstyle/checkstyle-result.xml', unHealthy: ''
		}
	}		
	
	stage("Email"){
	  // sh 'set'
	   emailext attachLog: true, body: '$DEFAULT_CONTENT', mimeType: 'text/html', postsendScript: '$DEFAULT_POSTSEND_SCRIPT', presendScript: '$DEFAULT_PRESEND_SCRIPT', recipientProviders: [[$class: 'CulpritsRecipientProvider'], [$class: 'RequesterRecipientProvider'], [$class: 'DevelopersRecipientProvider'], [$class: 'FailingTestSuspectsRecipientProvider'], [$class: 'FirstFailingBuildSuspectsRecipientProvider']], replyTo: '$DEFAULT_REPLYTO', subject: '$DEFAULT_SUBJECT', to: 'wangk@ordinov.com,dengfx@ordinov.com,$DEFAULT_RECIPIENTS'    

	}
	
	stage ("fClean"){ 
		sh 'mvn clean'
	}	
	
	stage("end"){ 
		sh 'date' 
	} 
}