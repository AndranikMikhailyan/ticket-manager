select x.id, x.dep_city, x.arriv_city, x.sum, min(x.sum)
from (
select r.id id, r.departure_city dep_city, r.arrival_city arriv_city, sum(t.price) sum
from tickets t
inner join routes r on t.route_id = r.id
where quarter(t.departure_date) = quarter(current_date()) and t.ticket_status = 'BOUGHT'
group by r.id) x;