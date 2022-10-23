Imagine a modular insurance product. People can choose from 4 modules. Each module has a different
selectable coverage and a different mathematical risk.
<br><br>
These are the modules:
- Bike (Coverage 0-3k, Risk 30%)
- Jewelry (Coverage 500-10k, Risk 5%)
- Electronics (Coverage 500-6k, Risk 35%)
- Sports Equipment (Coverage 0-20k, Risk 30%)
<br>

The user should be able to select the coverage for each module and see the calculated price. The price of
the tariff, which is the individual configuration for each customer, should be calculated based on the risk:
<br><br>
`[price] = [coverage]*[risk]`
<br><br>
The solution should store calculated prices and make them accessible via an endpoint.
<br><br>

**Run the registration service first**

<code>mvn clean install package

cd registration/target

java -jar registration-1.0.jar
</code>

**Run the other services**

<code>cd bike/target

java -jar bike-1.0.jar
</code>

... the same for all other serivices

**Check if all services have been registered to the registration service**
http://localhost:1111
