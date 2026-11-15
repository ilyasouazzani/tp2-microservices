$ErrorActionPreference='Stop'
Set-Location $PSScriptRoot
foreach($service in @('discovery','config','customer-service','inventory-service','billing-service','gateway')) {
 $log=Join-Path $PSScriptRoot "$service.log"
 Start-Process java -ArgumentList @('-jar',"$service/target/$service-1.0.0.jar") -WorkingDirectory $PSScriptRoot -WindowStyle Hidden -RedirectStandardOutput $log -RedirectStandardError (Join-Path $PSScriptRoot "$service.error.log")
 Start-Sleep -Seconds 10
}
Write-Host 'Services lancés. Consulter les logs et http://localhost:8761.'
